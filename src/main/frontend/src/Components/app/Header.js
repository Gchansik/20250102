import React from "react";
import {AuthContext} from "../context/AuthProvider";
import main from "./Main";

function Header() {
    const {auth, setAuth} = useContext(AuthContext);

    return(
        <header>
//         메인화면
            <main>

            </main>

//      게시판

//  회원 정보

        </header>
    );

}
export default Header;