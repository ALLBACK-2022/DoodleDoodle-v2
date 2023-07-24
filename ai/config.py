import os
from dotenv import load_dotenv

# main.py의 상위 디렉토리 경로를 얻습니다.
current_directory = os.path.dirname(os.path.abspath(__file__))
parent_directory = os.path.join(current_directory, '../..')

# .env 파일의 경로를 설정합니다.
env_file_path = os.path.join(parent_directory, '.env')

# .env 파일에서 환경 변수를 로드
load_dotenv(dotenv_path=env_file_path)

# AWS 액세스 키와 비밀 키를 환경 변수로부터 가져옵니다.
AWS_ACCESS_KEY = os.environ.get('AWS_ACCESS_KEY')
AWS_SECRET_KEY = os.environ.get('AWS_SECRET_KEY')

# AWS S3 버킷 정보
BUCKET_NAME = os.environ.get('BUCKET_NAME')
BUCKET_REGION = os.environ.get('BUCKET_REGION')
