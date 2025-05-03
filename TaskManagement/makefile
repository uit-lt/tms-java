docker-build:
	cp .env.example .env
	./scripts/generate-nginx-config.sh
	docker compose up --build
docker-up:
	cp .env.example .env
	./scripts/generate-nginx-config.sh
	docker compose up
docker-down:
	docker compose down
