import React, { useState, useEffect, useRef } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

import DrawingCanvas from '../components/DrawingCanvas';
import NextButton from '../components/NextButton';
import ClearButton from '../components/ClearButton';
import WordText from '../components/WordText';
import PlayerText from '../components/PlayerText';
import GameBGImg from '../components/GameBGImg';
import Loading from '../components/Loading';

const backBaseUrl = process.env.REACT_APP_BACKEND_URL;
const postImageToBackURL = `${backBaseUrl}/draws`; // 백엔드에 이미지 보내는 API
const getResultOne = `${backBaseUrl}/results/draws/`;
const getResultMany = `${backBaseUrl}/results/games/`;

// 게임 페이지
function GamePage() {
  const [randWord, setRandWord] = useState(''); // 그림을 그릴 단어
  const [maxPlayer, setMaxPlayer] = useState(); // 전체 플레이어 수
  const [currentPlayer, countPlayer] = useState(1); // 현재 플레이어 번호
  const [isLoad, setIsLoad] = useState(false);

  const location = useLocation(); // 이전 페이지에서 받아온 데이터
  const navigate = useNavigate(); // 네비게이트 선언(다음페이지 이동 시 사용할 함수

  const canvasRef = useRef(); // DrawingCanvas컴포넌트의 함수를 불러오기위한 ref
  const gameID = useRef(); // 게임 ID
  const successCount = useRef(0);
  const canDrawing = useRef(true);

  const drawIdArray = useRef([]);

  // 플레이어수, 게임ID, 단어를 이전 페이지에서 받아와서 업데이트
  function setGameData() {
    setRandWord(location.state.drawWord);
    setMaxPlayer(location.state.playerNum);
    gameID.current = location.state.gameID.id;
    drawIdArray.current.length = 0;
    successCount.current = 0;
  }

  // 페이지 로드 시 1회 실행, 게임 Data 세팅 및 캔버스 기본 세팅
  useEffect(() => {
    setGameData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  async function postImageToBack(imgFile) {
    const formData = new FormData();
    formData.append('file', imgFile);
    // formData.append('gameId', gameID.current);
    // formData.append('playerNo', currentPlayer);
    const headers = {
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET,POST,PUT,DELETE,OPTIONS',
      'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With',
      'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
    };
    await axios
      .post(`${postImageToBackURL}/games/${gameID.current}/player-no/${currentPlayer}`, formData, headers)
      .then(response => {
        drawIdArray.current[currentPlayer - 1] = response.data.draw_id; // 반환값에서 drawID받아서 저장
      })
      .catch(error => console.log(error));
  }

  async function goToNextPage() {
    if (maxPlayer === 1) {
      await axios
        .get(getResultOne.concat(drawIdArray.current[currentPlayer - 1]))
        // 호출이 완료되면
        .then(response => {
          if (response.data === '') {
            setTimeout(function d() {
              goToNextPage();
            }, 250);
          } else {
            navigate('../resultone', {
              replace: true,
              state: {
                gameId: gameID.current,
                drawId: drawIdArray.current,
                isFromGamePage: true,
              },
            });
          }
        })
        .catch(error => console.log(error));
    } else {
      await axios
        .get(getResultMany.concat(gameID.current))
        // 호출이 완료되면
        .then(response => {
          if (response.data === '' || response.data.results.length !== maxPlayer) {
            setTimeout(function d() {
              goToNextPage();
            }, 250);
          } else {
            navigate('../resultmany', {
              replace: true,
              state: {
                gameId: gameID.current,
                drawId: drawIdArray.current,
                isFromGamePage: true,
              },
            });
          }
        })
        .catch(error => console.log(error));
    }
  }

  // DrawingCanvas에서 이미지 로딩 완료후 호출
  // imageData를 받아서 파일객체 생성후 Backend에 전송
  // 마지막 플레이어면 1인 or 다인용 결과페이지로 이동(state로 id들 전달)
  const imgDataPost = async data => {
    // 파일객체 생성 및 백엔드에 저장
    const metadata = { type: 'image/png' };
    const file = new File([data], ''.concat(gameID.current, '_', currentPlayer, '.png'), metadata);
    if (currentPlayer < maxPlayer) countPlayer(current => current + 1);
    await postImageToBack(file).then(() => {
      if (currentPlayer >= maxPlayer) goToNextPage();
    });
  };

  // NextButton을 클릭했을때 실행
  const nextButtonClick = () => {
    if (!isLoad) {
      if (currentPlayer >= maxPlayer) {
        canvasRef.current.setCanDrawing();
        setIsLoad(true);
        // console.log('d');
      }
      canvasRef.current.convertCanvasToImage();
    } else console.log('loding...');
  };

  // ClearButton을 클릭했을때 실행
  const clearButtonClick = () => {
    canvasRef.current.clearCanvas();
  };

  return (
    <div className="w-screen h-screen bg-primary relative select-none">
      <GameBGImg pageName="GamePage" />
      {isLoad && <Loading />}
      <DrawingCanvas ref={canvasRef} imgDataPost={imgDataPost} canDrawing={canDrawing} />
      <PlayerText currentPlayer={currentPlayer} maxPlayer={maxPlayer} />
      <WordText randWord={randWord} />
      <ClearButton clearButtonClick={clearButtonClick} />
      <NextButton nextButtonClick={nextButtonClick} isMaxPlayer={currentPlayer >= maxPlayer} />
    </div>
  );
}

export default GamePage;
