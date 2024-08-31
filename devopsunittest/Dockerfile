# Use the official Python image
FROM python:3.9-slim

# Set the working directory
WORKDIR /app

# Copy the requirements file and install dependencies
COPY requirements.txt /app/
RUN pip install --no-cache-dir -r requirements.txt


# Copy the test script into the container
COPY . /app/

# Run the test script
CMD ["/bin/sh", "-c", "sleep infinity"]
