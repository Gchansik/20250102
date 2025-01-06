import axios from "axios";
import {useEffect, useContext, useState} from "react";
import {useNavigate} from "react-router";

import {AuthContext} from "../context/AuthProvider";
import {HttpHeaderContext} from "../context/HttpHeaderProvider";

import MemberUpdate from "./MemberUpdate";


function CheckPwd() {
    const {headers, setHeaders} = useContext(HttpHeaderContext);

    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [pwd, setPwd] = useState("");

    const [showMemberUpdate, setShowMemberUpdate] = useState(false);

    const ChangeEmail = (event) => {
        setEmail(event.target.value);
    }

    const ChangeName = (event) => {
        setName(event.target.value);
    }

    const ChangePwd = (event) => {
        setPwd(event.target.value);
    }

    useEffect( () => {
        setHeaders({
            Authorization: `Bearer ${localStorage.getItem("bbs_access_token")}`,
        });
    }, []);

    const passwordCheck = async() => {
        const req = {
            password: pwd,
        };

        try {
            await axios
                .post("http://localhost:8080/user/checkPwd", req, {headers: headers})
                .then((resp) => {
                    console.log(resp.data);
                    setEmail(resp.data.email);
                    setName(resp.data.username);
                    setShowMemberUpdate(true);
                })
        } catch ((err) => {
            console.log(err);

            alert(err.response.data);

            const resp = err.response;
            if (resp.status === 400) {
                alert(resp.data);
            }
        })

    };


  return (
    <div>
      {showMemberUpdate ? (
        <MemberUpdate email={email} name={name} />
      ) : (
        <>
          <table className="table">
            <tbody>
              <tr>
                <th>비밀번호</th>
                <td>
                  <input
                    type="password"
                    value={pwd}
                    onChange={changePwd}
                    size="50px"
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <br />

          <div className="my-3 d-flex justify-content-center">
            <button
              className="btn btn-outline-secondary"
              onClick={passwordCheck}
            >
              <i className="fas fa-user-plus"></i>비밀번호 확인
            </button>
          </div>
        </>
      )}
    </div>
  );











}
export default CheckPwd;