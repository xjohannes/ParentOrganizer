import faker
import json
import random

# Initialize a Faker instance
fake = faker.Faker()

# Load parent IDs from parents.json
with open('./data/parents.json', 'r') as f:
    parents = json.load(f)
    parent_ids = [parent['id'] for parent in parents]

# Load location IDs from locations.json
with open('./data/locationData.json', 'r') as f:
    locations = json.load(f)
    location_ids = [location['id'] for location in locations]

# Define a list of task names
task_names = ["Trafikkvakt", "Cafe", "Basar", "10.klasse cafe", "Pizzaovn", "Riddertelt", "Rake løv", "male gjerde", "Lykkejul"]

# Define a function to generate a taskData object
def generate_taskData():

    return {
        "taskName": random.choice(task_names),
        "leader": random.choice(parent_ids),
        "location": random.choice(location_ids),
        "description": fake.text(40),
        "dateCreated": str(fake.date_time_this_year()),
        "dateUpdated": str(fake.date_time_this_year())
    }

# Generate 130 taskData objects
taskDatas = [generate_taskData() for _ in range(80)]

# Write the taskData objects to a JSON file
with open('./data/taskData.json', 'w') as f:
    json.dump(taskDatas, f, indent=4)
