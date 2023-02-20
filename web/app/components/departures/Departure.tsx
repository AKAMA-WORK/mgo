import { Button, Card, Form, Image, InputNumber, Typography,Switch  } from "antd";
import React, { ReactNode, useEffect,useState} from "react";
import { Departure } from "~/types/departure/Departure";
import PlaceNumberInput from "../form/PlaceNumberInput";
import DepartureVehiculeSeats from "./DepartureVehiculeSeats";
import SeatsLegend from "./SeatsLegend";
import { Modal } from "antd";
import { Link, useFetcher, useLocation } from "@remix-run/react";
import Skeleton from "../Skeleton/Skeleton";


interface DeparturePreviewIconProps {
    icon: ReactNode
    name: string
    value: string
}

const DeparturePreviewIcon = ({ icon, name, value }: DeparturePreviewIconProps) => {

    return (
        <div className="app-app-departure-preview-icon">
            <div>{icon}</div>
            <div className="app-app-departure-preview-icon-name">{name}</div>
            <div>{value}</div>
        </div>
    )
}
interface DeparturePreviewProps {
    departure: Departure
    seatsSelection?: boolean
    showConfirmButton?: boolean
}

const DeparturePreview = ({ departure, seatsSelection, showConfirmButton }: DeparturePreviewProps) => {
    let fetcher = useFetcher();
    let location = useLocation();
    let optimisticSeats = departure.seatSelection?.seats || [];

    if (fetcher.submission) {
        let values = Object.fromEntries(fetcher.submission.formData);
        if (typeof values.seat === "string") {
            optimisticSeats = [...(departure?.seatSelection?.seats || []), parseInt(values.seat, 10)];
        }

    }
    const redirectUrl = location.pathname + location.search


    const price = (
        <div className="app-departure-preview-body-price">
            <div className="app-departure-preview-body-price-item app-departure-preview-body-price-not-last">
                <div className="app-departure-preview-body-price-item-title">Nombre de places</div>
                <div className="app-departure-preview-body-price-item-content">
                    {optimisticSeats.length}
                </div>
            </div>
            <div className="app-departure-preview-body-price-item app-departure-preview-body-price-not-last">
                <div className="app-departure-preview-body-price-item-title">Prix du billet</div>
                <div className="app-departure-preview-body-price-item-content">{departure.price} Ar</div>
            </div>
            <div className="app-departure-preview-body-price-item app-departure-preview-body-price-last">
                <div className="app-departure-preview-body-price-item-title">Total</div>
                <div className="app-departure-preview-body-price-item-content">{departure.price * optimisticSeats.length} Ar</div>
            </div>
            {!seatsSelection && showConfirmButton && <div className="app-departure-preview-body-price-action">
                <fetcher.Form method="post" action="/seat">
                    <input type="hidden" name="_action" defaultValue={"add-seat-selection"} />
                    <input
                        key={redirectUrl}
                        type="hidden"
                        name="redirect"
                        defaultValue={redirectUrl}
                    />
                    <input
                        key={departure.departureId}
                        type="hidden"
                        defaultValue={departure.departureId}
                        name="departureId"
                    />
                    <Button type="primary" htmlType="submit">Réserver</Button>
                </fetcher.Form>
            </div>}
        </div>
    )
    const descriptionExtra = (departure.config || departure.vehicle || departure.estimatedArrivalTime) ? (
        <div className="app-departure-preview-body-description-extra">
            {departure.config?.authorizedLuggage ? <div>
                <span className="app-departure-preview-body-description-extra-title">Poids maximal de bagage : </span> <span>{departure.config.authorizedLuggage} kg</span>
            </div> : null}
            {departure.config?.priceExtraLuggage ? <div>
                <span className="app-departure-preview-body-description-extra-title">Prix de l’excédent de bagage : </span> <span> {departure.config.priceExtraLuggage} Ar/kg</span>
            </div> : null}
            {departure?.vehicle?.carRegistrationNumber ? <div>
                <span className="app-departure-preview-body-description-extra-title">Immatriculation du véhicule : </span> <span>{departure.vehicle.carRegistrationNumber}</span>
            </div> : null}
            {departure.estimatedArrivalTime ? <div>
                <span className="app-departure-preview-body-description-extra-title">Heure d’arrivée estimée : </span> <span>{departure.estimatedArrivalTime}</span>
            </div> : null}
        </div>
    ) : null

    const [visible, setVisible] = React.useState(false);


    /*useEffect(() => {
        setVisible(true)
    }, [])*/

    const id = `modalMount${Math.random() * 1000}`.substring(0, 12)

    const [loading, setLoading] = useState(false);

    useEffect(() => {
      setLoading(true);
      const timing = setTimeout(() => {
        setLoading(false);
      }, 771500);
      return () => clearTimeout(timing);
    }, []);

    return (
        <>
                     
                <>
                    <Modal title={<Typography.Title level={5}>Tickets epuisés</Typography.Title>} open={visible} getContainer={`#${id}`}

                            onCancel={() => setVisible(prev => !prev)}
                            cancelText="Annuler"
                            okText="Rechercher un autre départ"
                        >
                            <Typography.Paragraph>
                                Il semblerait qu’il ne reste plus aucune place disponible pour ce départ. Veuillez rechercher un nouveau départ.
                            </Typography.Paragraph>
                    </Modal>
                    <Card
                            //   onClick={() => setVisible(prev => !prev)}
                            id={id}
                            className={`app-departure-preview ${seatsSelection ? 'app-departure-preview-selection-seats' : ''}`}
                            title={<div className="app-departure-preview-title">
                                <div className="app-departure-preview-title-company">{departure.organization.name}</div>
                                {departure.category && <div className="app-departure-preview-title-category">{departure.category.name}</div>}
                            </div>}
                        >
                                <div className="app-departure-preview-body">
                                    <div className="app-departure-preview-body-company">
                                        <Image src={departure.organization.logo} alt={departure.organization.name} />
                                    </div>
                                    <div className="app-departure-preview-body-description">
                                        <div className="app-departure-preview-body-description-vehicule">
                                            <DeparturePreviewIcon
                                                icon={<svg width="41" height="40" viewBox="0 0 41 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M20.5 9.33341C22.6097 9.33341 24.672 9.959 26.4261 11.1311C28.1802 12.3031 29.5474 13.969 30.3547 15.9181C31.1621 17.8672 31.3733 20.0119 30.9617 22.081C30.5501 24.1502 29.5342 26.0508 28.0425 27.5426C26.5507 29.0343 24.6501 30.0502 22.581 30.4618C20.5118 30.8734 18.3671 30.6621 16.4181 29.8548C14.469 29.0475 12.8031 27.6803 11.631 25.9262C10.4589 24.172 9.83334 22.1097 9.83334 20.0001C9.83652 17.1721 10.9613 14.4608 12.961 12.4611C14.9607 10.4614 17.672 9.33659 20.5 9.33341ZM20.5 6.66675C17.8629 6.66675 15.2851 7.44873 13.0924 8.91382C10.8998 10.3789 9.19079 12.4613 8.18162 14.8976C7.17245 17.334 6.9084 20.0149 7.42287 22.6013C7.93734 25.1877 9.20722 27.5635 11.0719 29.4282C12.9366 31.2929 15.3124 32.5627 17.8988 33.0772C20.4852 33.5917 23.1661 33.3276 25.6025 32.3185C28.0388 31.3093 30.1212 29.6003 31.5863 27.4077C33.0514 25.215 33.8333 22.6372 33.8333 20.0001C33.8333 16.4639 32.4286 13.0725 29.9281 10.572C27.4276 8.07151 24.0362 6.66675 20.5 6.66675V6.66675ZM21.076 13.6454L21.3787 11.1121C21.3859 11.0555 21.3809 10.9979 21.3641 10.9434C21.3473 10.8889 21.319 10.8386 21.2811 10.7959C21.2432 10.7532 21.1966 10.7191 21.1444 10.6959C21.0923 10.6727 21.0358 10.661 20.9787 10.6614H19.8867C19.8296 10.661 19.7731 10.6727 19.7209 10.6959C19.6688 10.7191 19.6222 10.7532 19.5843 10.7959C19.5464 10.8386 19.5181 10.8889 19.5013 10.9434C19.4844 10.9979 19.4794 11.0555 19.4867 11.1121L19.7893 13.6454C19.8005 13.7435 19.8476 13.8339 19.9214 13.8994C19.9952 13.9649 20.0907 14.0007 20.1893 14.0001H20.6747C20.7736 14.001 20.8693 13.9653 20.9435 13.8998C21.0176 13.8343 21.0648 13.7437 21.076 13.6454ZM20.676 26.0001H20.1907C20.092 25.9994 19.9966 26.0353 19.9227 26.1008C19.8489 26.1662 19.8019 26.2567 19.7907 26.3547L19.488 28.8881C19.4808 28.9447 19.4857 29.0022 19.5026 29.0568C19.5194 29.1113 19.5477 29.1616 19.5856 29.2043C19.6235 29.247 19.6701 29.2811 19.7223 29.3043C19.7744 29.3274 19.8309 29.3392 19.888 29.3387H20.9813C21.0384 29.3392 21.0949 29.3274 21.1471 29.3043C21.1992 29.2811 21.2458 29.247 21.2837 29.2043C21.3216 29.1616 21.3499 29.1113 21.3668 29.0568C21.3836 29.0022 21.3886 28.9447 21.3813 28.8881L21.0787 26.3547C21.0674 26.2562 21.02 26.1654 20.9456 26.0999C20.8712 26.0343 20.7751 25.9988 20.676 26.0001ZM26.788 20.6427L29.3213 20.9454C29.378 20.9526 29.4355 20.9477 29.49 20.9308C29.5446 20.914 29.5949 20.8857 29.6375 20.8478C29.6802 20.8099 29.7143 20.7633 29.7375 20.7112C29.7607 20.659 29.7725 20.6025 29.772 20.5454V19.4534C29.7725 19.3963 29.7607 19.3398 29.7375 19.2877C29.7143 19.2355 29.6802 19.1889 29.6375 19.151C29.5949 19.1131 29.5446 19.0848 29.49 19.068C29.4355 19.0512 29.378 19.0462 29.3213 19.0534L26.788 19.3561C26.69 19.3673 26.5995 19.4143 26.534 19.4881C26.4686 19.562 26.4327 19.6574 26.4333 19.7561V20.2414C26.4324 20.3403 26.4681 20.4361 26.5336 20.5102C26.5991 20.5843 26.6897 20.6315 26.788 20.6427ZM14.4333 20.2427V19.7574C14.434 19.6587 14.3981 19.5633 14.3326 19.4895C14.2672 19.4156 14.1767 19.3686 14.0787 19.3574L11.5453 19.0547C11.4887 19.0475 11.4312 19.0525 11.3767 19.0693C11.3221 19.0862 11.2718 19.1145 11.2291 19.1524C11.1864 19.1903 11.1524 19.2368 11.1292 19.289C11.106 19.3412 11.0942 19.3977 11.0947 19.4547V20.5481C11.0942 20.6052 11.106 20.6617 11.1292 20.7138C11.1524 20.766 11.1864 20.8126 11.2291 20.8505C11.2718 20.8884 11.3221 20.9167 11.3767 20.9335C11.4312 20.9503 11.4887 20.9553 11.5453 20.9481L14.0787 20.6454C14.1772 20.6342 14.268 20.5868 14.3335 20.5124C14.3991 20.438 14.4346 20.3419 14.4333 20.2427ZM25.9 22.5387L22.4893 19.7974C22.4424 19.3452 22.2428 18.9225 21.9234 18.5989C21.604 18.2753 21.1839 18.0702 20.7323 18.0174C20.2807 17.9646 19.8246 18.0673 19.4392 18.3084C19.0538 18.5496 18.762 18.9149 18.612 19.3441L12.2333 24.6667C12.0768 24.797 11.9758 24.9822 11.9511 25.1843C11.9263 25.3865 11.9796 25.5905 12.1 25.7547L12.3933 26.1547C12.5127 26.3196 12.6905 26.4328 12.8904 26.4712C13.0903 26.5096 13.2974 26.4703 13.4693 26.3614L20.3267 22.0454L24.7373 24.3294C24.9125 24.4207 25.1152 24.4439 25.3065 24.3947C25.4977 24.3455 25.664 24.2272 25.7733 24.0627L26.076 23.5974C26.1845 23.4299 26.2264 23.2279 26.1936 23.031C26.1608 22.8342 26.0556 22.6567 25.8987 22.5334L25.9 22.5387Z" fill="#1276A1" />
                                                </svg>}
                                                name="Heure de départ"
                                                value={departure.time || '-'} />

                                            <DeparturePreviewIcon
                                                icon={<svg width="41" height="40" viewBox="0 0 41 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M34.132 18.5107L33.8653 16.2441C33.8451 16.0724 33.7624 15.9141 33.6331 15.7993C33.5038 15.6845 33.3369 15.6212 33.164 15.6214H32.552C32.4594 15.6214 32.3676 15.6397 32.2821 15.6751C32.1965 15.7106 32.1187 15.7625 32.0532 15.828C31.9877 15.8935 31.9358 15.9713 31.9003 16.0568C31.8649 16.1424 31.8467 16.2341 31.8467 16.3267V17.5641H31.0133C30.9667 17.1747 30.9133 16.7867 30.8493 16.4001L29.408 7.57208C29.366 7.31938 29.2358 7.08974 29.0405 6.92398C28.8452 6.75822 28.5975 6.66707 28.3413 6.66675H14.6293C14.3732 6.66707 14.1254 6.75822 13.9301 6.92398C13.7348 7.08974 13.6046 7.31938 13.5627 7.57208L12.1187 16.4067C12.0547 16.7934 12.0013 17.1814 11.9547 17.5707H11.1213V16.3334C11.1213 16.1468 11.0474 15.9678 10.9157 15.8356C10.784 15.7034 10.6053 15.6288 10.4187 15.6281H9.80532C9.63242 15.6279 9.46547 15.6912 9.33617 15.806C9.20688 15.9208 9.12425 16.079 9.10399 16.2507L8.83732 18.5174C8.82565 18.6163 8.83505 18.7165 8.86489 18.8115C8.89473 18.9065 8.94435 18.9941 9.01047 19.0685C9.07659 19.1429 9.15773 19.2025 9.24853 19.2434C9.33934 19.2842 9.43776 19.3053 9.53732 19.3054H10.4187C10.5952 19.3049 10.7651 19.238 10.8946 19.1179C11.0241 18.9979 11.1036 18.8335 11.1173 18.6574H11.852C11.7987 19.3419 11.772 20.0272 11.772 20.7134V32.3814C11.7737 32.634 11.8752 32.8758 12.0542 33.054C12.2332 33.2323 12.4753 33.3327 12.728 33.3334H14.2653C14.5185 33.3317 14.7608 33.2299 14.9394 33.0504C15.1179 32.8708 15.2183 32.628 15.2187 32.3747V30.4187H27.752V32.3747C27.7523 32.6289 27.8535 32.8725 28.0332 33.0522C28.2129 33.2319 28.4565 33.3331 28.7107 33.3334H30.248C30.5022 33.3334 30.7461 33.2324 30.9259 33.0526C31.1056 32.8728 31.2067 32.629 31.2067 32.3747V20.7067C31.2067 20.0205 31.18 19.3352 31.1267 18.6507H31.8613C31.8749 18.8254 31.9533 18.9887 32.0811 19.1086C32.2089 19.2285 32.3768 19.2963 32.552 19.2987H33.4293C33.5291 19.2991 33.6278 19.2782 33.719 19.2375C33.8101 19.1969 33.8915 19.1373 33.958 19.0628C34.0244 18.9884 34.0742 18.9006 34.1042 18.8055C34.1342 18.7103 34.1437 18.6098 34.132 18.5107ZM14.8947 12.0574C14.926 11.8493 15.0309 11.6593 15.1905 11.522C15.35 11.3848 15.5535 11.3093 15.764 11.3094H27.2067C27.4171 11.3093 27.6206 11.3848 27.7802 11.522C27.9397 11.6593 28.0447 11.8493 28.076 12.0574L29.0427 18.4347C29.0427 18.4347 27.352 17.7334 21.4853 17.7334C15.6187 17.7334 13.928 18.4347 13.928 18.4347L14.8947 12.0574ZM14.152 24.9121C13.981 24.9123 13.8116 24.8787 13.6536 24.8134C13.4955 24.7481 13.3519 24.6523 13.2309 24.5314C13.11 24.4105 13.014 24.267 12.9485 24.1091C12.883 23.9511 12.8493 23.7818 12.8493 23.6107V22.1054H15.44V22.6667C15.4388 23.461 15.6656 24.2389 16.0933 24.9081L14.152 24.9121ZM17.6 27.7121C17.6007 27.3687 17.7375 27.0396 17.9804 26.7969C18.2234 26.5542 18.5526 26.4178 18.896 26.4174H24.0773C24.4207 26.4178 24.7499 26.5542 24.9929 26.7969C25.2358 27.0396 25.3726 27.3687 25.3733 27.7121H17.6ZM26.236 22.6667C26.2353 23.4388 25.9475 24.1829 25.4285 24.7544C24.9094 25.3259 24.1964 25.6839 23.428 25.7587C23.332 25.7681 23.232 25.7721 23.1307 25.7721H19.84C19.0164 25.7721 18.2265 25.4449 17.6442 24.8625C17.0618 24.2802 16.7347 23.4903 16.7347 22.6667V22.1014H26.236V22.6667ZM30.1227 23.6001C30.1223 23.9453 29.9849 24.2763 29.7406 24.5203C29.4964 24.7644 29.1652 24.9014 28.82 24.9014H26.8773C27.3051 24.2322 27.5318 23.4543 27.5307 22.6601V22.0947H30.1227V23.6001Z" fill="#1276A1" />
                                                </svg>}
                                                name="Type de véhicule"
                                                value={departure.vehicleType?.name || ''} />


                                            <DeparturePreviewIcon
                                                icon={<svg width="41" height="40" viewBox="0 0 41 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M29.0147 33.3334H12.5347C12.2223 33.3336 11.9187 33.2294 11.6723 33.0373C11.4259 32.8453 11.2508 32.5764 11.1747 32.2734L10.5413 29.7401C10.4896 29.5335 10.4857 29.3178 10.5299 29.1095C10.5741 28.9012 10.6652 28.7057 10.7964 28.5379C10.9275 28.3701 11.0952 28.2345 11.2867 28.1413C11.4782 28.0481 11.6884 27.9998 11.9013 28.0001H29.648C29.8608 28 30.0709 28.0484 30.2622 28.1417C30.4535 28.235 30.621 28.3707 30.752 28.5385C30.883 28.7062 30.974 28.9016 31.0182 29.1099C31.0623 29.3181 31.0584 29.5336 31.0067 29.7401L30.3733 32.2734C30.2973 32.5762 30.1223 32.8449 29.8762 33.0369C29.6301 33.229 29.3268 33.3333 29.0147 33.3334ZM23.7173 15.2001H17.832C17.6145 15.2011 17.4054 15.284 17.2463 15.4323C17.0872 15.5805 16.9897 15.7832 16.9733 16.0001L16.1267 26.6667H25.4227L24.576 16.0001C24.5596 15.7832 24.4621 15.5805 24.303 15.4323C24.1439 15.284 23.9348 15.2011 23.7173 15.2001ZM27.6467 14.2667C27.5915 13.6487 27.3073 13.0736 26.85 12.6543C26.3927 12.2349 25.7952 12.0016 25.1747 12.0001H16.3747C15.7542 12.0016 15.1567 12.2349 14.6993 12.6543C14.242 13.0736 13.9579 13.6487 13.9027 14.2667L12.7747 26.6667H15.056L15.9093 15.9094C15.95 15.4267 16.1699 14.9767 16.5257 14.648C16.8816 14.3193 17.3476 14.1357 17.832 14.1334H23.7173C24.2018 14.1357 24.6678 14.3193 25.0236 14.648C25.3795 14.9767 25.5994 15.4267 25.64 15.9094L26.4933 26.6667H28.7747L27.6467 14.2667ZM24.108 10.0001V7.33341C24.108 7.1566 24.0378 6.98703 23.9127 6.86201C23.7877 6.73699 23.6181 6.66675 23.4413 6.66675H18.108C17.9312 6.66675 17.7616 6.73699 17.6366 6.86201C17.5116 6.98703 17.4413 7.1566 17.4413 7.33341V10.0001C17.4413 10.1769 17.5116 10.3465 17.6366 10.4715C17.7616 10.5965 17.9312 10.6667 18.108 10.6667H23.4413C23.6181 10.6667 23.7877 10.5965 23.9127 10.4715C24.0378 10.3465 24.108 10.1769 24.108 10.0001Z" fill="#1276A1" />
                                                </svg>}
                                                name="Places disponibles"
                                                value={departure.availableSeats?.toString() || ''} />

                                        </div>
                                        {!seatsSelection && descriptionExtra}
                                        {seatsSelection && (
                                            <>
                                                <div className="app-departure-preview-body-selection-seats-description-extra-price">
                                                    {descriptionExtra}
                                                    {price}

                                                </div>
                                                <div className="app-departure-preview-body-selection-seats-seats-legend">
                                                    <SeatsLegend />
                                                </div>
                                            </>
                                        )}
                                    </div>
                                    {!seatsSelection && price}
                                    {seatsSelection && (
                                        <div className="app-departure-preview-body-selection-seats">
                                            <DepartureVehiculeSeats showConfirmButton={showConfirmButton} departure={departure} selected={departure?.seatSelection?.seats || []} />
                                        </div>
                                    )}
                                </div>
                    </Card>
                </> 
         
        </>
    )

}


export default DeparturePreview;