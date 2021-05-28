# base image
FROM node:16.0.0

WORKDIR /Mediscreen-front
# add `/app/node_modules/.bin` to $PATH
ENV PATH /Mediscreen-front/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package.json /Mediscreen-front/package.json
RUN npm install --legacy-peer-deps
COPY . /Mediscreen-front
# start app
CMD ng serve --host 0.0.0.0