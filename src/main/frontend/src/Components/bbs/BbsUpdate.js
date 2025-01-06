import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
//회원
import {AuthContext, AuthProvider} from "../context/AuthProvider";
import {HttpHeadersContext, HttpHeadersProvider} from "../context/HttpHeadersProvider";
//css
import "../../css/bbsupdate.css";


function BbsUpdate() {

    const [auth, setAuth] = useContext(HttpHeadersContext);
    const [headers, setHeaders] = useContext(HttpHeadersContext);
    const navigate = useNavigate();

    //주소 설정
    const location = useLocation();
    const {bbs} = location.state;

    //게시판
    const boardId = bbs.boardId;
    const [title, setTitle] = useState(bbs.title);
    const [content, setContent] = useState(bbs.content);
    const [files, setFiles] = useState([]);
    const [severFiles, setSeverFiles ] = useState(bbs.files || []);

    //changeTitle
    const changeTitle = (event) => {
        setTitle(event.target.value);
    };

    //changeContent
    const changeContent = (event) => {
        setContent(event.target.value);
    };

    //file
    const handleChangeFile = (event) => {
        //총 5개까지만 허용
        const selectedFiles = Array.from(event.target.files).slice(0,5);
        setFiles((prevFiles) => [...prevFiles, ...selectedFiles]);
    };

    //filter() - 주어진 배열의 일부에 대한 얕은 복사본을 생성
    const handleRemoveFile = (index) => {
        setFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
    };

    //setServerFiles
    const handleRemoveServerFile = (index, boardId, filesId) => {
        setServerFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
        fileDelete(boardId, fileId);
    };

    //인증 - token
    useEffect(() => {
        // 컴포넌트가 렌더링될 때마다 localStorage의 토큰 값으로 headers를 업데이트
        setHeaders({
            Authorization: `Bearer ${localStorage.getItem("bbs_access_token")}`,
        });
        }, []);

    const fileUpload = async(boardId) => {

        // 파일 데이터 저장
        const fd = new FormData();
        files.forEach((file) => fd.append("file", file));

        try {
            await axios
            .post(`http://localhost:8989/board/${boardId}/file/upload`, fd, { headers: headers })
            .then((resp) => {
                        console.log("[BbsUpdate.js] fileUpload() success :D");
                        console.log(response.data);
            })
        } catch (err) {
            console.log("[BbsUpdate.js] fileUpload() error :<");
            console.log(err);
        }
    }

    const fileDelete = async(boardId) => {
        try {
            await axios
            .delete((`http://localhost:8989/board/${boardId}/file/delete`)
            .then((resp) => {
                console.log("[BbsWrite.js] fileDelete() success :D");
                console.log(resp.data);

                alert("파일 삭제 성공 :D");
            })
        } catch ((err) {
            console.log("[BbsUpdate.js] fileDelete() error :<");
            console.log(err);
        });
    }

    const updateBbs = async(boardId) => {
        try {
            await axios
            .patch(`http://localhost:8989/board/${boardId}/update`)
            .then((resp) => {
                        console.log("[BbsUpdate.js] updateBbs() success :D");
                        console.log(response.data);
            })
        } catch (err) {
            console.log("[BbsUpdate.js] updateBbs() error :<");
            console.log(err);
        }
    }



//HTML -----------------------------------------------------------
    return(
        <div>
            <table className="table">
                <tbody>
                    <tr>
                        <th className="table-primary">작성자</th>
                        <td>
                            <input
                                type="text"
                                className="form-control"
                                value={bbs.writerName("id")}
                                size="50px"
                                readOnly
                            />
                        </td>
                    </tr>
                    <tr>
                        <th className="table-primary">제목</th>
                        <td>
                            <input
                                type="text"
                                className="form-control"
                                value={title}
                                size="50px"
                                onChange={changeTitle}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th className="table-primary">내용</th>
                        <td>
                            <textarea
                                type="text"
                                className="form-control"
                                value={content}
                                rows="10"
                                onChange={changeContent}
                            />
                        </td>
                    </tr>
                    <tr>
                    <th className="table-primary">파일</th>
                        <td>
                        {severFiles.length > 0 || files.length > 0 ? (
                            <div className='file-box'>
                                <ul>
                                    {/* 기존의 파일 데이터, 삭제 로직 */}
                                    {severFiles.map((file, index) => (
                                    <li key={file.fileId} style={{ display: 'flex', justifyContent: 'space-between',
                                    alignItems: 'center' }}>
                                        <span>
                                            <strong>File Name:</strong> {file.originFileName} &nbsp;
                                            <button className="delete-button" type="button"
                                            onClick={() => handleRemoveSeverFile(index, boardId, file.fileId)}>
                                                x
                                            </button>
                                        </span>
                                    </li>
                                    ))}
                                    {/* 새로운 파일을 저장할 때 */}
                                    {files.map((file, index) => (
                                    <li key={file.fileId} style={
                                        { display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                    <span>
                                        <strong>File Name:</strong> {file.name} &nbsp;
                                        <button className="delete-button" type="button" onClick={() => handleRemoveFile(index)}>
                                            x
                                        </button>
                                    </span>
                                    </li>
                                    ))}
                                </ul>
                            </div>
                        ) : (
                            <div className='file-box'>
                                <p>No files</p>
                            </div>
                        )}
                    <div className="file-select-box">
                        <input type="file" name="file" multiple="multiple" onChange={handleChangeFile}></input>
                    </div>
                    </td>
                  </tr>
                </tbody>
            </table>

        {/* 수정하기 - 버튼  */}
        <div className="my-3 d-flex justify-content-center">
            <button className="btn btn-dark" onClick={updateBbs}>
                <i className="fas fa-pen"></i> 수정하기</button>
        </div>


        </div>

    );



}
export default BbsUpdate;