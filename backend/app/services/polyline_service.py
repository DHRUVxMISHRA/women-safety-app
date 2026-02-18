# decode the polyline into coordinates

import polyline
import numpy as np

def decode_and_sample(encoded): # polyline
    coords = polyline.decode(encoded) # [(lat, lng), ...]

    step = int(max(1,len(coords) // 25))# if coords is < 25 then we take all
    coords = coords[::step]

    return np.array(coords, dtype=np.float32)
