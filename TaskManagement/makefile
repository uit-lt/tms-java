docker-build-prod:
	cp .env.example .env
	./scripts/generate-nginx-config.sh
	docker compose -f docker-compose.yml up --build
docker-build:
	cp .env.example .env
	./scripts/generate-nginx-config.sh
	docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build
docker-up:
	docker compose -f docker-compose.yml -f docker-compose.dev.yml up
docker-down:
	docker compose -f docker-compose.yml -f docker-compose.dev.yml down
