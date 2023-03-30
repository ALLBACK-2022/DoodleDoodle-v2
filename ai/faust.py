import faust

app = faust.App('DoodleDoodle', broker='kafka://localhost:9092', key_serializer='raw')

result_schema = faust.Schema(
    key_type=str,
    value_type=bytes
)

result_topic = app.topic(
    'doodledoodle.to.ai.draw',
    schema=result_schema,
)
