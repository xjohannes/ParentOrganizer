import faker
import json
import random

# Initialize a Faker instance
fake = faker.Faker()

# Load parent IDs from parents.json
with open('./data/parentsWithId.json', 'r') as f:
    parents = json.load(f)
parent_ids = [parent['id'] for parent in parents]

# Load location IDs from locations.json
with open('./data/taskDataWithId.json', 'r') as f:
    tasks = json.load(f)
    task_ids = [task['id'] for task in tasks]

times = ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00']

id = 0


# Define a function to generate a taskData object
def generate_taskData():
    # Generate a date
    date_object = fake.date_between()

    # Convert the date object to a string
    date_string = date_object.strftime("%Y-%m-%d")

    # Generate a random index for the start time
    start_time_index = random.randint(0, len(times) - 2)
    # Generate a random index for the end time that is greater than the start time index
    end_time_index = random.randint(start_time_index + 1, len(times) - 1)

    return {
        "task": random.choice(task_ids),
        "parent": random.choice(parent_ids),
        "watchDate": date_string,
        "startTime":times[start_time_index],
        "endTime": times[end_time_index],
    }


# Generate 130 taskData objects
taskDatas = [generate_taskData() for _ in range(230)]

# Write the taskData objects to a JSON file
with open('./data/watchData.json', 'w') as f:
    json.dump(taskDatas, f, indent=4)
