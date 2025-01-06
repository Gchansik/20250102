import axios from "axios";
import {useEffect, useContext} from "react";
import {useNavigate} from "react-router";

import {AuthContext} from "../context/AuthProvider";
import {HttpHeaderContext} from "../context/HttpHeaderProvider";



function Logout() {
    const [auth, setAuth] = useContext(AuthContext);

    const useNavigate = useNavigate(AuthContext);

    const logout = () => {
        localStorage.removeItem("bbs_access_token");
        localStorage.removeItem("id");

        alert(auth + "님 성공적으로 로그아웃 됐습니다.");
        setAuth(null);
        navigate("/");

    };

    useEffect( () => {
        logout();
    }, []);


}
export default Logout;