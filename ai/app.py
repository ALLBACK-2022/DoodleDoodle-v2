from tensorflow import keras
from skimage.transform import resize
from faust import app, result_topic
import matplotlib.pyplot as plt
import numpy as np
import tempfile
from connection import s3_connection, s3_download_image

@app.agent(result_topic)
async def search(result):
    async for res in result:
        
        s3 = s3_connection()
        random_word = str(res['randomWord'])
        draw_id = str(res['drawId'])  
        file_name = f"{draw_id}.png"
        with tempfile.TemporaryDirectory() as temp_dir:
            local_file_path = os.path.join(temp_dir, file_name)
            file = s3_download_image(s3, file_name, local_file_path)
            
            with open("./ai-model/class_names.txt", "r", encoding="utf8") as file:
                class_names = []
                for line in file:
                    class_names.append(line.rstrip('\n'))
                    
            model = keras.models.load_model('./model/keras.h5')

            image = plt.imread(image_file)
            image = image[:, :, 0]
            image = resize(image, (28, 28))

            for x in range(0, 28):
                for y in range(0, 28):
                    num = image[x][y] - 1
                    if num < 0:
                        num *= -1
                    image[x][y] = num

            pred = model.predict(np.expand_dims(image, axis=0))[0]
            ind = (-pred).argsort()[:]

            response, result = {}, {}

            for x in range(0, len(ind)):
                if(class_names[ind[x]] == random_word):
                    result[class_names[ind[x]]] = round(pred[ind[x]]*100, 2)
                if x < 5:
                    response[class_names[ind[x]]] = round(pred[ind[x]]*100, 2)
        response['result'] = result
        response['drawId'] = draw_id

        sink = app.topic('doodledoodle.to.backend.result', value_type=dict)
        await sink.send(value=response)
