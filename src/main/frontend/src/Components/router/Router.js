import {Routes, Route} from 'react-router-dom';

import Home from '../app/Home';
import BbsList from '../bbs/BbsList';
import BbsWrite from '../bbs/BbsWrite';
import BbsDetail from '../bbs/BbsDetail';
import BbsAnswer from '../bbs/BbsAnswer';
import BbsUpdate from '../bbs/BbsUpdate';


import CheckPwd from '../member/CheckPwd';
import Join from '../member/Join';
import Login from '../member/Login';
import Logout from '../member/Logout';
import MemberUpdate from '../member/MemberUpdate';




function Router() {
    return(
        <Routes>
            <Route path="/" element={<Home />}></Route>

            <Route path="/bbslist" element={<BbsList />}></Route>
            <Route path="/bbswrite" element={<BbsWrite />}></Route>
            <Route path="/bbsdetail/:boardId" element={<BbsDetail />}></Route>
            <Route path="/bbsupdate" element={<BbsUpdate />}></Route>
            <Route path="/bbsanswer/parentSeq" element={<BbsAnswer />}></Route>

            <Route path="/login" element={<Login />}></Route>
            <Route path="/join" element={<Join />}></Route>
            <Route path="/checkpwd" element={<CheckPwd />}></Route>
            <Route path="/update" element={<MemberUpdate />}></Route>
            <Route path="/logout" element={<Logout />}></Route>


        </Routes>


    );
}
export default Router;
