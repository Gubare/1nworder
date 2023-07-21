from gtts import gTTS
from time import time


class NNTextToSpeech:
    # создать аудиофайл в директории и вернуть название файла
    def create_speech(self, text, language):
        # tеxt - текст на каком-то языке
        # language - метка языка (ru, en)
        tts = gTTS(text, lang=language)
        file_name = str(time()) + ".mp3"
        tts.save(file_name)
        return file_name


