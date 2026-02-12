import replicate
# import os
from dotenv import load_dotenv

load_dotenv() #contains api

# request and response from api
def generate_response(messages: list):
    output = replicate.run(
        "openai/gpt-5",
        input={
            "messages": messages,
            "max_output_tokens": 200

        }
    )

    return "".join(output)
