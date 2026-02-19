from app.db.mongodb import user_collection
from app.services.aadhaar import encrypt_aadhar

def add_user(data: dict):
    id = user_collection.count_documents({})
    encrypted_aadhaar = encrypt_aadhar(data["aadhaar"])
    data["aadhaar"] = encrypted_aadhaar
    data["user_id"] = id+100 # 100,101,102
    user_collection.insert_one(data)
    return data["user_id"]

