FROM node:20-alpine

#copying from the host machine -> container
COPY package.json package-lock.json .

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "start"]