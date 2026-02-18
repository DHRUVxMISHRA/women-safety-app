from app.db.mongodb import user_collection


def add_user(data: dict):
    id = user_collection.count_documents({})

    data["user_id"] = id+100 # 100,101,102
    return user_collection.insert_one(data)

