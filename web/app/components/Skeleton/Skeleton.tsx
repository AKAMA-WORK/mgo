import React from 'react'

export default function Skeleton() {
    return (
        <div>
             <div className="skeleton" >
                    <div className="skeleton-left flex1">
                       <div className="square"> </div>
                    </div>
                    <div className="skeleton-center flex2">

                        <div className="icons">
                            <div className="icon  wicon"></div>
                            <div className="icon  wicon"></div>
                            <div className="icon  wicon"></div>
                        </div>

                        <div className="line h17 w40 m10"> </div>
                        <div className="line h17 w40 m10"> </div>
                        <div className="line h17 w40 m10"> </div>
                        <div className="line h17 w40 m10"> </div>
                        <div className="line h17 w40 m10"> </div>
                        <div className="line h17 w40 m10"> </div>
                    </div>
                    <div className="skeleton-rigth flex1">
                       <div className="square"> </div>
                    </div>

            </div>
        </div>
    )
}
