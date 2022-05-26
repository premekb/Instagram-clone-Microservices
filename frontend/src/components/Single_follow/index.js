import React from "react";
import {Link} from "react-router-dom";

const testname = "Buri"
const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

export const Single_follow = () => {
    return (
        <div className={"follower-box"}>
            <div className="modal-profile-photo">
                <div className={"modal-profile-main"}>
                    <Link to='/profile'>
                        <div className={"modal-profile-user-profilepicture"}>
                            <img src={testImage} alt={testname} className="modal-profile-user-profilepicture"/>
                        </div>
                    </Link>
                </div>
            </div>
            <div className={'center-height'}>
                {testname}
            </div>
            {/*TODO button follow, unfollow / podle reality*/}
            <div className={'center-height'}>
                <button className={'follow-button'}>
                    Follow
                </button>
            </div>

        </div>
    );
}

export default Single_follow;