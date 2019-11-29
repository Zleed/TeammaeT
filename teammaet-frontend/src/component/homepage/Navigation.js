import React from "react";
import {Col, Nav, Navbar, Row} from "react-bootstrap";

export default function Navigation(props) {
    return(
                <Navbar className="bg-light justify-content-between">
                    <Navbar.Brand href="/">IDareU</Navbar.Brand>
                    {localStorage.length === 0 ? (
                        <Nav activeKey="/">
                            <Nav.Item>
                                <Nav.Link href="/login">Login</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link href="/registration">Register</Nav.Link>
                            </Nav.Item>
                        </Nav>):(
                        <Nav>
                            <Nav.Item>
                                <Nav.Link href="/" onClick={() => {
                                    localStorage.clear();
                                }}>Logout</Nav.Link>
                            </Nav.Item>
                        </Nav>
                    )
                    }
                </Navbar>


    )
}