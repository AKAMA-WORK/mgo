
const SeatsLegend = () => {


    return (
        <div className="app-seats-legend">
            <div className="app-seats-legend-item">
                <img src="/images/seats/driver.svg"/>
                <span>Chauffer</span>
            </div>
            <div className="app-seats-legend-item">
                <img src="/images/seats/unavailable.svg"/>
                <span>Indisponible</span>
            </div>
            <div className="app-seats-legend-item">
                <img src="/images/seats/available.svg"/>
                <span>Disponible</span>
            </div>
            <div className="app-seats-legend-item">
                <img src="/images/seats/selected.svg"/>
                <span>Sélectionnée</span>
            </div>
        </div>
    )
}

export default SeatsLegend