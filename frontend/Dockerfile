FROM node:16.13.1
USER root

WORKDIR /frontend
COPY . /frontend
RUN npm install
ARG API_URL
ENV REACT_APP_HOST_IP_ADDRESS $API_URL

RUN npm run build
