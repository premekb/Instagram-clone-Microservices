// src/components/Header/index.js

import React from "react";
import "./Header.css";
import logo from '../../images/logo.png'

class Header extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const nickname = this.props.nickname;
        const profilepicture = this.props.profilepicture;

        return (
            <nav>
                <div className="header-line">
                    <a href={"../../App.js"}><img className="logo" src={logo} alt="Logo"/></a>
                    <a href={"../../App.js"} className="title">Onegram</a>
                    <div className="Post-user-profilepicture profile-image" >
                        <a href={"../Profile_dashboard/index.js"}>
                            <img src={profilepicture} alt={nickname} />
                        </a>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Header;