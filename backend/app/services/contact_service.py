from app.db.mongodb import emergency_contacts_collection

def add_emergency_contact(contact_data: dict):
    return emergency_contacts_collection.insert_one(contact_data)

def get_user_contacts(user_id: str):
    contacts = emergency_contacts_collection.find({"user_id":user_id})
    return list(contacts)
