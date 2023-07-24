from tensorflow import keras
from skimage.transform import resize
from faust_config import app, result_topic
from PIL import Image
import numpy as np, matplotlib.pyplot as plt, base64, io

@app.agent(result_topic)
async def search(result):
    async for res in result:
        random_word = str(res['englishName'])
        image_file = res['file']
        draw_id = str(res['drawId'])

        class_names = []
        with open("./model/class_names.txt", "r", encoding="utf8") as file:
            for line in file:
                class_names.append(line.rstrip())

        model = keras.models.load_model('./model/keras.h5')
        image_file = Image.open(io.BytesIO(base64.b64decode(image_file)))

        image = np.array(image_file)[:, :, 0]
        image = resize(image, (28, 28))

        for x in range(0, 28):
            for y in range(0, 28):
                num = image[x][y] - 1
                if num < 0:
                    num *= -1
                image[x][y] = num

        pred = model.predict(np.expand_dims(image, axis=0))[0]
        ind = (-pred).argsort()[:]
        
        response, result, top_five = {}, {}, {}
        
        for x in range(0, len(ind)):
            if(class_names[ind[x]] == random_word):
                result[class_names[ind[x]]] = round(pred[ind[x]]*100, 2)
            if x < 5:
                top_five[class_names[ind[x]]] = round(pred[ind[x]]*100, 2)

        response['result'] = result
        response['draw_id'] = draw_id
        response['top_five'] = top_five

        sink = app.topic('doodledoodle.to.backend.result', value_type=dict)
        await sink.send(value=response)

if __name__ == "__main__":
    app.main()
