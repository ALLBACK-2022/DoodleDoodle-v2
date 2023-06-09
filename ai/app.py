from tensorflow import keras
from skimage.transform import resize
from faust import app, result_topic
import matplotlib.pyplot as plt
import numpy as np

@app.agent(result_topic)
async def search(result):
    async for res in result:
        
        random_word = str(res['randomWord'])
        image_file = res['file']
        draw_id = str(res['drawId'])
        
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
