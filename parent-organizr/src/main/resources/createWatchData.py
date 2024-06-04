import faker
import json

# Initialize a Faker instance
fake = faker.Faker()

# Define a function to generate a parent object
def generate_watchData():
    return {
        "firstName": fake.first_name(),
        "lastName": fake.last_name(),
        "email": fake.email(),
        "phoneNumber": fake.phone_number(),
        "version": "1.0.0"
    }

# Generate 30 watchData objects
watchDatas = [generate_watchData() for _ in range(130)]

# Write the watchData objects to a JSON file
with open('./data/watchDatas.json', 'w') as f:
    json.dump(watchDatas, f, indent=4)
