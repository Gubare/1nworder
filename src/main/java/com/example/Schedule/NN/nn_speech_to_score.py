import torch
import torchaudio

# "сравнение" двух последовательностей символов (сравнение двух строк)
def get_score(target_seq, current_seq):
    score = 0
    target_vocab = {char: 1 for char in target_seq}
    for char in current_seq:
        if char in target_vocab: score += 1
    return score / len(target_seq)


class NNSpeechToScore:
    # создание внутри объекта других объектов
    def __init__(self):
        # этот объект содержит в себе некоторые данны, нужные для оценки речь
        bundle = torchaudio.pipelines.WAV2VEC2_ASR_BASE_960H

        # частота дискретизации аудиоданных
        self.sample_rate = bundle.sample_rate

        # символы английского алфавита
        self.labels = bundle.get_labels()

        # нейронная сеть в виде класса фреймворка torch
        self.model = bundle.get_model()

    # преобразовать аудиофайл в последовательность символов английского алфавита
    def process_speech_file(self, speech_file):
        # speech_file - название аудиофайла

        # waveform - torch-тензор(массив) с аудиоданными
        # sample_rate - частота дискретизации аудиоданных
        waveform, sample_rate = torchaudio.load(speech_file)

        if sample_rate != self.sample_rate:
            waveform = torchaudio.functional.resample(waveform, sample_rate, self.sample_rate)

        with torch.inference_mode():
            # получить символы английского алфавита в векторном
            # представлении после обработки нейросетью
            emission, _ = self.model(waveform)

        # преобразование векторного представления символов в строковое представление
        indices = torch.argmax(emission, dim=-1)
        indices = torch.unique_consecutive(indices, dim=-1)
        indices = [i for i in indices[0] if i != 0 and i != 1]
        return "".join([self.labels[i] for i in indices])

    # "сравнить" "эталонную" речь с речью пользователя (0% - 100%)
    def get_score(self, target_file, current_file):
        # target_file - аудиофайл, "эталонное" произношение
        # current_file - название аудиофайла с речью пользователя

        # аудиофайл(речь) преобразованный в полседовательность английских символов
        target_seq = self.process_speech_file(target_file)

        # аудиофайл(речь) преобразованный в полседовательность английских символов
        current_seq = self.process_speech_file(current_file)
        return get_score(target_seq, current_seq)



# nn_speech_to_score = NNSpeechToScore()
# print(nn_speech_to_score.get_score(target_file="output.mp3", current_file="output.mp3"))


