import Title from "~/components/typography/Title";
import { Link } from "@remix-run/react";

interface FloatingProps {
    img: string
    text1: string
    text2:string
}
const FloatingCard = ({ img, text1,text2 }: FloatingProps) => {
    return (
        <Link  to="/#join-us"className="floatingDiv">
            <img src={img} alt="" />
            <span>
                <Title level={4} color="color1" align="center">
                    {text1}
                </Title>
                <Title level={5} color="color9" align="center">
                  
                       {text2}
                 
                </Title>
            </span>
        </Link>
    )
}

export default  FloatingCard
