import React, {useEffect, useContext} from 'react'
import {Row, Col} from 'react-bootstrap'
import '../style.css';
import {UserContext} from "../context/UserContext";
import User from "../component/user/User";
import UserButtons from "../component/user/UserButtons";
import Axios from "axios";

export default function UserPage(props) {

    const {userMethods} = useContext(UserContext);
    const id = props.match.params.id;

    useEffect(() => {
        userMethods.getUser(id);
        let outside;
        Axios.get(`http://localhost:8080/user/${id}/avatar`, {
            headers: {
                "Authoization": `Bearer ${localStorage.getItem("token")}`,
                responseType: 'arraybuffer'
            }
        })
            .then(response => {
                const buffer = Buffer.from(response.data, 'base64');
            })
            .catch(ex => {
                console.error(ex);
            });
    }, [id]);


    return (
        <Row className="user-page">
            <Col className="user">
                <User/>
                <Row className="user-buttons">
                    <UserButtons id={id}/>
                </Row>
            </Col>
        </Row>
    )
}
