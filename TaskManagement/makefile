PROD_COMPOSE_FILE := docker-compose.prod.yml
DEV_COMPOSE_FILE := docker-compose.dev.yml

docker-build-prod:
	cp .env.example .env
	./scripts/generate-nginx-config.sh
	docker-compose -f ${PROD_COMPOSE_FILE} up --build
docker-down-prod:
	docker-compose -f ${PROD_COMPOSE_FILE} down
docker-build:
	cp .env.example .env
	./scripts/generate-nginx-config.sh
	docker-compose -f ${PROD_COMPOSE_FILE} -f ${DEV_COMPOSE_FILE} up --build
docker-up:
	docker-compose -f ${PROD_COMPOSE_FILE} -f ${DEV_COMPOSE_FILE} up
docker-down:
	docker-compose -f ${PROD_COMPOSE_FILE} -f ${DEV_COMPOSE_FILE} down
