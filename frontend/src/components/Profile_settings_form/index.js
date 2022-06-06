import React from "react";
import './profile-settings-form.css'
import './profile-settings-form.css'
import axios from "axios";
import * as Constants from "../../gql/query";
import ImageUploading from "react-images-uploading";

export const Profile_settings_form = () => {

    let data = JSON.parse(localStorage.getItem('userData'));
    let username = data.username;
    let bio = data.bio;
    let link = data.link;
    let isPublic = data.isPublic;

    function SaveChanges() {
        let formData = new FormData();

        if (images[0]) {
            const query = `{"query":"mutation{\\n\\tsetProfilePhoto\\n}"}`
            formData.append("operations", query);
            formData.append("file", images[0].file);
            let xhr = new XMLHttpRequest();
            xhr.open('POST', Constants.USER_GRAPHQL_API, true);
            xhr.setRequestHeader('Authorization', "Bearer " + localStorage.getItem('token'));
            xhr.send(formData);
        }

        let potencialNewLink = document.getElementById('link').value;
        if (potencialNewLink !== link) {
            const CHANGE_LINK = `mutation{
        editLink(input:{
             link:"` + potencialNewLink + `"
        })}`
            axios.post(Constants.USER_GRAPHQL_API, {
                    query: CHANGE_LINK
                },
                {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                .then(res => console.log(res))
                .catch(err => console.log(err))
        }

        // TODO - otestovat
        let potencialNewUsername = document.getElementById('username').value;
        if (potencialNewUsername !== username) {
            const CHANGE_USERNAME = `mutation{
        editUsername(input:{
             bio:"` + potencialNewUsername + `"
        })}`
            axios.post(Constants.USER_GRAPHQL_API, {
                    query: CHANGE_USERNAME
                },
                {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                .then(res => console.log(res))
                .catch(err => console.log(err))
        }

        let potencialNewBio = document.getElementById('bio').value;
        if (potencialNewBio !== bio) {
            const CHANGE_BIO = `mutation{
        editBio(input:{
             bio:"` + potencialNewBio + `"
        })}`
            axios.post(Constants.USER_GRAPHQL_API, {
                    query: CHANGE_BIO
                },
                {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                .then(res => console.log(res))
                .catch(err => console.log(err))
        }
    }

    const [images, setImages] = React.useState([]);
    const onChange = (imageList, addUpdateIndex) => {
        setImages(imageList);
    };

// TODO asynchronne + nastaveni private uctu
    if (isPublic) {
        let switchPublic = document.querySelector('#isPublicInput');
        if (switchPublic) {
            switchPublic.checked = true
        }
    }

    let requestsData;

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_MY_FOLLOW_REQUESTS
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        requestsData = r.data.data.followRequests;
        localStorage.setItem('requestsData', JSON.stringify(requestsData));
    })

    return (
        <form className={"settings-form"}>
            <label htmlFor="username">Username:</label><br/>
            <input type="text" id="username" name="username" defaultValue={username}/><br/>
            <label htmlFor="bio">Bio:</label><br/>
            <textarea id="bio" name="bio" rows="8" cols="50" maxLength={255} defaultValue={bio}>
            </textarea><br/>
            <label htmlFor="link">Link:</label><br/>
            <input type="text" id="link" name="link" defaultValue={link}/><br/>
            <div className={"setting-photo"}>
                <div className={"Post-user-profilepicture profile-image"}>
                    <img src={data.image}/>
                </div>
                {/*TODO / Stylovani file buttonu*/}
                <div className={"setting-photo"}>
                    <ImageUploading
                        value={images}
                        onChange={onChange}
                        dataURLKey="data_url"
                    >
                        {({
                              imageList,
                              onImageUpload,
                              isDragging,
                              dragProps
                          }) => (
                            <div className="upload__image-wrapper">
                                <button
                                    style={isDragging ? {color: "blue"} : null}
                                    onClick={onImageUpload}
                                    {...dragProps}
                                >
                                    Click and Drop here
                                </button>
                                &nbsp;
                                {imageList.map((image, index) => (
                                    <div key={index} className="image-item">
                                        <img src={image.data_url} alt="" width="100"/>
                                    </div>
                                ))}
                            </div>
                        )}
                    </ImageUploading>
                </div>
            </div>
            <br/>
            <div className={'is-public'}>
                Public profile:
                <label className="switch">
                    <input id='isPublicInput' type="checkbox"/>
                    <span className="slider round"/>
                </label>
            </div>
            <br/>
            <button id={'submit'} className={"accept-button"} type={"button"} onClick={SaveChanges}>Save changes
            </button>

        </form>

    );
}

export default Profile_settings_form;
