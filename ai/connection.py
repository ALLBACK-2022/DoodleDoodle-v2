import boto3
from config import AWS_ACCESS_KEY, AWS_SECRET_KEY 
from config import BUCKET_NAME,BUCKET_REGION

# s3에 connection 하는 과정 
def s3_connection():
    s3 = boto3.client('s3', region_name = BUCKET_REGION, aws_access_key_id = AWS_ACCESS_KEY,aws_secret_access_key = AWS_SECRET_KEY)
    return s3

# 이미지 uri 가져오는 api
def s3_get_image_url(s3, filename : str):
    """
    s3 : 연결된 s3 객체(boto3 client)
    filename : s3에 저장된 파일 명
    """
    return f'https://{BUCKET_NAME}.s3.{BUCKET_REGION}.amazonaws.com/{filename}' 

def s3_download_image(s3, object_name, local_file_path):
    # s3=boto3.client('s3')
    # s3.download_file('presentation-storage','UserPowerpoint/test6.txt',"down_file.txt")
    file = s3.download_file(s3 = s3, bucket = BUCKET_NAME, object_name = object_name, local_file_path = local_file_path)
    return file