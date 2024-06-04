import faker
import json
import random

# Initialize a Faker instance
fake = faker.Faker()

# Define a list of task names
place = ["Storskolen", "Lilleskolen", "Krysset"]
building = ["Førsteklassehuset", "Det blå huset", "Det røde bygget", "Hovedhuset", "Salen", "Ute"]
room = ["2.klasserommet", "3.klasserommet", "4.klasserommet", "Fritids", "Kjøkkenet", "Skolekjøkken", "Lillesalen", "Pizzaovnen", "Foran hovedhuset"]

# Define a function to generate a taskData object
def generate_locationData():
    start_time = fake.time_object()
    end_time = fake.time_object()
    # Ensure end_time is after start_time
    while end_time <= start_time:
        end_time = fake.time_object()

    return {
        "place": random.choice(place),
        "building": random.choice(building),
        "room": random.choice(room),
        "floor": fake.random_int(min=0, max=3),
        "description": fake.text(),
        "date_created": str(fake.date_time_this_year()),
        "date_updated": str(fake.date_time_this_year()),
        "version": "1.0.0"
    }

# Generate 130 taskData objects
locationDatas = [generate_locationData() for _ in range(30)]

# Write the taskData objects to a JSON file
with open('./data/locationData.json', 'w') as f:
    json.dump(locationDatas, f, indent=4)
