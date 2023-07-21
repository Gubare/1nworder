
from nn_text_to_speech import NNTextToSpeech
from nn_speech_to_score import NNSpeechToScore
import os
from time import sleep
import sys


class NNModule:
    def __init__(self):
        self.nn_text_to_speech = NNTextToSpeech()
        self.nn_speech_to_score = NNSpeechToScore()

    # синтез речи из текста
    def create_speech(self, target_text, target_language):
        # target_text - текст, который нужно озвучить
        # target_language - метка языка (ru, en)
        return self.nn_text_to_speech.create_speech(target_text, target_language)

    # "оценка" качества речи по аудиоданным
    def get_score(self, target_text, target_language, current_file):
        # target_text - текст, который нужно озвучить
        # target_language - метка языка (ru, en)
        # current_file - название аудиофайла с речью пользователя

        # аудиофайл, озвучка текста (синтезированная речь)
        target_file = self.create_speech(target_text, target_language)
        score = self.nn_speech_to_score.get_score(target_file, current_file)

        dir_path = os.getcwd()  # путь до текущей директории
        os.remove(dir_path + '/' + target_file)  # удалить файл из директории
        os.remove(current_file)
        return score


# выполнить команду с джавы
def process_server_command(nn_module, file_lines):
    # file_lines - список с данными

    # файл, в который запишется результат работы нейронных сетей
    java_command_file = "java_command_file.txt"
    text = file_lines[0]
    language = file_lines[1]
    user_speech_file = file_lines[2]  # название файла с речью пользователя
    command = file_lines[3]

    # сначала происходит обработка данных нейросетями,
    # потом записывается информация в файл(который будет обработан джавой)
    if command == "text_to_speech":
        speech_file = nn_module.create_speech(text, language)
        with open(java_command_file, 'w', encoding="utf-8") as java_file:
            java_file.write(speech_file + '\n')
            java_file.write("0")
            java_file.write("text_to_speech")

    elif command == "speech_to_score":
        score = nn_module.get_score(text, language, user_speech_file)
        with open(java_command_file, 'w', encoding="utf-8") as java_file:
            java_file.write("no speech file")
            java_file.write(str(score) + '\n')
            java_file.write("speech_to_score")


def new_main():
    text = 'python'
    if len(sys.argv) > 1:
        text = sys.argv[1]
    nn_module = NNModule()
    file_name = nn_module.create_speech(text, 'en')
    # print(file_name)
   #user_audio = r'C:\Users\Artic\IdeaProjects\Schedule2\src\main\java\com\example\Schedule\NN\audio.mp3'
    user_audio = 'C:/Users/Artic/IdeaProjects/Schedule2/src/main/java/com/example/Schedule/NN/audio.mp3'
    # import shutil
    # shutil.copy(user_audio, myaudio)
    score = nn_module.get_score(text, 'en', user_audio)
    print(score)




# text
# language
# user speech audio file
# name command

if __name__ == "__main__":
    def main():
        nn_module = NNModule()

        dir_path = os.getcwd()  # путь до текущей директории
        python_command_file = "python_command_file.txt"  # файл для указаний с сайта

        while True:
            dir_paths = os.listdir(dir_path)  # считать все файлы в текущей директории

            # если в директории есть файл с указаниями, то начать его обрабатывать
            if python_command_file in dir_paths:

                # открыть файл с указаниями
                with open(python_command_file, 'r', encoding="utf-8") as python_file:
                    file_lines = list()

                    # записать список данных из файла
                    for line in python_file.readlines():
                        file_lines.append(line[:-1])

                # удалить файл с указаниями (он создается в джаве)
                # os.remove(dir_path + '/' + command_file)

                # выполнить все преобразования связанные с нейронными сетями
                process_server_command(nn_module, file_lines)

            sleep(1)  # сделать задержку цикла в 1 секунду, т. к. будет зависать)

    new_main()

