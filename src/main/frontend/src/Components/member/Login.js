/* 로그인 컴포넌트 */

import axios from "axios";
import {useState, useContext} from "react";
import {useNavigate} from "react-router";

import {AuthContext} from "../context/AuthProvider";
import {HttpHeaderContext} from "../context/HttpHeaderProvider";

function Login() {
    const [auth, setAuth] = useContext(AuthContext);
    const [headers, setHeaders] = useContext(HttpHeaderContext);

    const useNavigate = useNavigate();

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");

    const ChangeId = (event) => {
        setId(event.target.value);
    }

    const ChangePwd = (event) => {
        setPwd(event.target.value);
    }

    const login = async() => {
        const req = {
            email: id,
            password: pwd,
        };

        await axios
            .post("http://localhost:8080/user/login", req)
            .then((resp) => {
                console.log(resp.data);
                alert(resp.data.email+"님 성공적으로 로그인 되었습니다.");

                localStorage.setItem("bbs_access_token", resp.data.token);
                localStorage.setItem("id", resp.data.email);

                //사용자 인증 정보 - 아이디 저장
                setAuth(resp.data.email);

                setHeaders({Authorization: `Bearer ${resp.data.token}` });

                navigate("/bbslist");
            })
            .catch((err)=> {
                console.log(err);

                alert(err.response.data);

                const resp = err.response;
                if (resp.status === 400) {
                    alert(resp.data);
                }
            });

    }


  return (
    <div>
      <table className="table">
        <tbody>
          <tr>
            <th className="col-3">아이디</th>
            <td>
              <input type="text" value={id} onChange={changeId} size="50px" />
            </td>
          </tr>

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

      <div className="my-1 d-flex justify-content-center">
        <button className="btn btn-outline-secondary" onClick={login}>
          <i className="fas fa-sign-in-alt"></i> 로그인
        </button>
      </div>
    </div>
  );


}
export default Login;




