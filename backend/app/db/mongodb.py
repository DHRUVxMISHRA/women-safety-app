from pymongo import MongoClient
import os

MONGO_URI = os.getenv("MONGO_URI")

client = MongoClient(MONGO_URI)

db = client["women_safety"]

emergency_contacts_collection = db["emergency_contacts"]
conversation_collection = db["conversations"]