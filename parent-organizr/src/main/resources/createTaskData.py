import faker
import json
import random

# Initialize a Faker instance
fake = faker.Faker()

# Load parent IDs from parents.json
with open('./data/parentsWithId.json', 'r') as f:
    parents = json.load(f)
    parent_ids = [parent['id'] for parent in parents]

id = 1 # Initialize id to 1

# Load location IDs from locations.json
with open('./data/locationsWithId.json', 'r') as f:
    locations = json.load(f)
    location_ids = [location['id'] for location in locations]

# Define a list of task names
task_names = ["Trafikkvakt", "Cafe", "Basar", "10.klasse cafe", "Pizzaovn", "Riddertelt", "Rake løv", "male gjerde", "Lykkejul"]

# Define a function to generate a taskData object
def generate_taskData():
    global id # Declare id as global to modify its value
    task =  {
        "id": id,
        "taskName": random.choice(task_names),
        "leader": random.choice(parent_ids),
        "location": random.choice(location_ids),
        "description": fake.text(40),
        "dateCreated": str(fake.date_time_this_year()),
        "dateUpdated": str(fake.date_time_this_year())
    }
    id += 1 # Increment id
    return task

# Generate 130 taskData objects
taskDatas = [generate_taskData() for _ in range(80)]

# Write the taskData objects to a JSON file
with open('./data/taskDataWithId.json', 'w') as f:
    json.dump(taskDatas, f, indent=4)
