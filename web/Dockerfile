# Use Node 12
# Exemple : docker build --build-arg APP_NAME=basis-app -t basis-app:v1 -f app.Dockerfile .
FROM node:14-alpine as builder

RUN apk add --no-cache git python3 py3-pip make build-base

ARG CURRENT_VERSION

ARG COMMIT_TIMESTAMP

ARG LAUNCH_SYNC

WORKDIR /app

COPY . /app/

RUN echo "Version : ${CURRENT_VERSION}"

RUN echo "Commit : ${COMMIT_TIMESTAMP}"

RUN sed -i "s#VERSION_APP#${CURRENT_VERSION}#g" ./app/Document.tsx

RUN yarn

RUN build

CMD ["yarn", "start"]