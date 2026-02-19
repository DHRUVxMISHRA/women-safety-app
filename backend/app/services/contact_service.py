from app.db.mongodb import MongoManager


def add_emergency_contact(contact_data: dict):
    emergency_contacts_collection = MongoManager.get_collection("emergency_contacts")
    return emergency_contacts_collection.insert_one(contact_data)

def get_user_contacts(user_id: str):
    emergency_contacts_collection = MongoManager.get_collection("emergency_contacts")

    contacts = emergency_contacts_collection.find({"user_id":user_id})
    return list(contacts)
