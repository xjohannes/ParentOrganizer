import faker
import json

# Initialize a Faker instance
fake = faker.Faker()

# Define a function to generate a parent object
def generate_pupil():
    return {
        "firstName": fake.first_name(),
        "lastName": fake.last_name(),
        "classId": fake.random_int(min=1, max=10),
        "version": "1.0.0"
    }

# Generate 30 parent objects
pupils = [generate_pupil() for _ in range(200)]

# Write the parent objects to a JSON file
with open('./data/pupils.json', 'w') as f:
    json.dump(pupils, f, indent=4)
