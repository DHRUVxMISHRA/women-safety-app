    package com.example.womensafetyapp.navigation

    import android.util.Log
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.womensafetyapp.models.RouteData
    import com.example.womensafetyapp.models.RoutesUiState
    import com.example.womensafetyapp.repo.RouteRepo
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class   SafeRouteViewModel @Inject constructor(
        private val routeRepo : RouteRepo
    ) : ViewModel() {

        private val _uiState = MutableStateFlow(RoutesUiState())
        val uiState : StateFlow<RoutesUiState> = _uiState



        fun setStartLocation(lat : Double, lng : Double) {

            _uiState.value = _uiState.value.copy(
                startLat = lat,
                startLng = lng
            )
        }


        fun setDestination(lat : Double, lng : Double){

            _uiState.value = _uiState.value.copy(
                destLat = lat,
                destLng = lng
            )
        }

        private val _safeRouteIndex = MutableStateFlow<Int?>(null)
        val safeRouteIndex : StateFlow<Int?> = _safeRouteIndex

        private val _routes = MutableStateFlow<List<RouteData>>(emptyList())
        val routes : StateFlow<List<RouteData>> = _routes


        fun getSafestRoute(
            originLat: Double,
            originLng: Double,
            destLat: Double,
            destLng: Double
        ) {
            viewModelScope.launch {
                try {
                    val response = routeRepo.getSafestRoute(
                        originLat,
                        originLng,
                        destLat,
                        destLng
                    )

                    _routes.value = response.routes ?: emptyList()
                    _safeRouteIndex.value = response.safestRouteIndex
                    if (response.routes == null) {
                        Log.d("SAFE_ROUTE", "Routes is NULL from backend")
                    } else {
                        Log.d("SAFE_ROUTE", "Routes size: ${response.routes.size}")
                    }
                    Log.d("SAFE_ROUTE", "Safest index: ${response.safestRouteIndex}")
                    Log.d("SAFE_ROUTE", "Response: $response")
                    Log.d("SAFE_ROUTE", "Routes: ${response.routes}")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


    }