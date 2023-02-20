import { Button } from "antd";
import { ArrowLeftOutlined, ArrowRightOutlined } from '@ant-design/icons';
import PlaceNumberInput from "../form/PlaceNumberInput";
import { Paging } from "~/types/Paging";


interface DeparturesPaginationProps {
    paging:Paging
    onPagingChange: (paging:Paging) => void
}

const DeparturesPagination = ({ paging, onPagingChange }: DeparturesPaginationProps) => {

     const hasNext = paging.currentPage<paging.totalPages;
     const hasPrevious = paging.currentPage>1;

     const handleNext = () => {
        onPagingChange({...paging, offset: paging.offset+paging.limit});
     }

     const handlePrevious = () => {
        onPagingChange({...paging, offset: paging.offset-paging.limit});

     }


    return (
        <div className="app-departures-list-pagination">
            <div className="app-departures-list-pagination-buttons">
                <Button disabled={!hasPrevious} onClick={handlePrevious} type="default" icon={<ArrowLeftOutlined />}>Page précédente</Button>
                <Button disabled={!hasNext} onClick={handleNext} icon={<ArrowRightOutlined />} className="secondary-btn">Page suivante</Button>
            </div>
            <div className="app-departures-list-pagination-pages">
                <PlaceNumberInput controls={false} value={paging.currentPage}/>
                <span> sur {paging.totalPages}</span>
            </div>
        </div>
    )

}

export default DeparturesPagination