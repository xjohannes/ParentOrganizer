import faker
import json
import random

# Initialize a Faker instance
fake = faker.Faker()

# Define a list of task names
place = ["Storskolen", "Lilleskolen", "Krysset"]
building = ["Førsteklassehuset", "Det blå huset", "Det røde bygget", "Hovedhuset", "Salen", "Ute"]
room = ["2.klasserommet", "3.klasserommet", "4.klasserommet", "Fritids", "Kjøkkenet", "Skolekjøkken", "Lillesalen", "Pizzaovnen", "Foran hovedhuset"]

id = 1  # Initialize id to 1

# Define a function to generate a taskData object
def generate_locationData():
    start_time = fake.time_object()
    end_time = fake.time_object()
    # Ensure end_time is after start_time
    while end_time <= start_time:
        end_time = fake.time_object()
    global id  # Declare id as global to modify its value
    location = {
        "id": id,
        "locationName": random.choice(room),
        "place": random.choice(place),
        "building": random.choice(building),
        "roomNr": fake.random_int(min=1, max=20),
        "floor": fake.random_int(min=0, max=3),
        "description": fake.text(40),
        "dateCreated": str(fake.date_time_this_year()),
        "dateUpdated": str(fake.date_time_this_year()),
    }
    id += 1  # Increment id
    return location

# Generate 130 taskData objects
locationDatas = [generate_locationData() for _ in range(30)]

# Write the taskData objects to a JSON file
with open('./data/locationsWithId.json', 'w') as f:
    json.dump(locationDatas, f, indent=4)
