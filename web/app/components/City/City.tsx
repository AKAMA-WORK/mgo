import { Typography } from "antd"
import { City } from "~/types/City"
import { LocationIcon } from "../icons/location-icon"
import Title from "../typography/Title"



interface CityPreviewProps {
    city: City
    iconPosition: 'left' | 'right'
}
const CityPreview = ({ city, iconPosition }: CityPreviewProps) => {

    const icon = (<div className="app-city-preview-icon">
        <LocationIcon />
    </div>)

    return <div className={`app-city-preview ${iconPosition}`}>
        {iconPosition === 'left' && icon}
        <div>
            <Title level={3}>{city.name}</Title>
        </div>
        {iconPosition === 'right' && icon}

    </div>

}

export default CityPreview