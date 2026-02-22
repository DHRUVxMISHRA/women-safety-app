from app.services.sampling_service import prepare_route_points
from app.ml_models.ml_model import predict_route_risk
import numpy as np


def rank_routes(routes):

    for i, route in enumerate(routes):

        # ---------- sampling ----------
        sampled_points = prepare_route_points(route.coordinates)

        # shape → (num_points, 2)

        # ---------- predict risk per point ----------
        point_risks = predict_route_risk(sampled_points)

        # ---------- aggregate route risk ----------
        route_risk = float(np.mean(point_risks))

        route.risk_score = route_risk
        route.index = i

    # ---------- safest route ----------
    safest_route = min(routes, key=lambda x: x.risk_score)

    return safest_route