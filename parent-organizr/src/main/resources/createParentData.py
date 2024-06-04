import faker
import json
import random

# Initialize a Faker instance
fake = faker.Faker()

def generate_norwegian_phone_number():
    # Generate a random number between 0 and 99
    xx = random.randint(0, 99)
    # Generate a random number between 1000000 and 9999999
    rest_of_number = random.randint(1000000, 9999999)
    # Combine the parts to form the phone number
    phone_number = f"+479{xx:02d}{rest_of_number}"
    return phone_number

# Define a function to generate a parent object
def generate_parent():
    return {
        "firstName": fake.first_name(),
        "lastName": fake.last_name(),
        "email": fake.email(),
        "phoneNumber": generate_norwegian_phone_number()

    }

# Generate 30 parent objects
parents = [generate_parent() for _ in range(30)]

# Write the parent objects to a JSON file
with open('./data/parents.json', 'w') as f:
    json.dump(parents, f, indent=4)
