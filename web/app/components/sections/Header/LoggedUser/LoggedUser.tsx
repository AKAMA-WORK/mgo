import { Avatar, Button, Dropdown, Menu, Space } from "antd";
import { User } from "~/types/User"
import { DownOutlined } from '@ant-design/icons';
import { Form, Link } from "@remix-run/react";
import { getUserFullName } from "~/utils/person-util";


interface LoggedUserProps {
    user: User
}

const LoggedUser = ({ user }: LoggedUserProps) => {

    const name = getUserFullName(user.name,user.person)
    const firstLetter = name?.charAt(0).toUpperCase() || '';


    const menu = (
        <Menu
            items={[
                {
                    label:  <Link to="people/me">{'  '}Edit Profile</Link>,
                    key: '0',
                },
                {
                    label:  <Link to="/people/me/bookings">Réservation</Link>,
                    key: '1',
                },
                {
                    label: <Form action="/users/logout" method="post"><Button htmlType="submit" type="link">Déconnexion</Button></Form>,
                    key: '2',
                }
            ]}
        />
    );



    return (
        <div className="app-logged-user">
            <Dropdown overlay={menu}>
                <a onClick={e => e.preventDefault()}>
                    <Space>
                        <Avatar className="bg-color1">{firstLetter}</Avatar>
                        <div>{name}</div>

                        <DownOutlined />
                    </Space>
                </a>
            </Dropdown>



        </div>
    )

}

export default LoggedUser