dc-up-b:
	docker compose up -d --build
dc-up:
	docker compose up -d
dc-down:
	docker compose down

mvn-clean-compile:
	docker compose run --rm -w /app spring_app mvn clean compile
	cp -r target/generated-sources/openapi/src/main/java/com/uit/tms/TaskManagement/model src/main/java/com/uit/tms/TaskManagement
	cp -r target/generated-sources/openapi/src/main/java/com/uit/tms/TaskManagement/api src/main/java/com/uit/tms/TaskManagement

cp-openapi:
	cp -r target/generated-sources/openapi/src/main/java/com/uit/tms/TaskManagement/model src/main/java/com/uit/tms/TaskManagement
	cp -r target/generated-sources/openapi/src/main/java/com/uit/tms/TaskManagement/api src/main/java/com/uit/tms/TaskManagement