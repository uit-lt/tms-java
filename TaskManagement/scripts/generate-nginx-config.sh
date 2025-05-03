#!/bin/bash

# Define project root (use absolute path)
# This assumes the script is located in ~/TaskManagement
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# List the variables you want
variables=(
  NGINX_HOST_NAME
  APP_HOST
  SPRING_LOCAL_HOST_PORT
)

# Build grep pattern and envsubst pattern
pattern=$(IFS='|'; echo "${variables[*]}")
subst_vars=$(printf '${%s} ' "${variables[@]}")

# Load .env file and export only selected variables
while IFS='=' read -r key value; do
  for var in "${variables[@]}"; do
    if [[ "$key" == "$var" ]]; then
      export "$key=$value"
    fi
  done
done < "$PROJECT_ROOT/.env"

template_file="$PROJECT_ROOT/docker/nginx/templates/tms.uit.local.template"
if [[ ! -f "$template_file" ]]; then
  echo "Template file not found: $template_file"
  exit 1
fi

config_file="$PROJECT_ROOT/docker/nginx/tms.uit.local.conf"
if [[ -f "$config_file" ]]; then
  echo "Config file already exists: $config_file"
  echo "Removing conf file ..."
  rm -f "$config_file"
fi

# Generate the nginx config using envsubst
envsubst "$subst_vars" < "$template_file" > "$config_file"
