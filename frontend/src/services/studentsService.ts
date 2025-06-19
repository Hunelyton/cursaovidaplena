import { NewStudentData } from './../screens/Teacher/Students/ModalCreateNewStudent/index'
import http from '../api/http'

interface DeleteStudentParams {
  studentId: string
}

interface CreateParams {
  newStudentData: NewStudentData
}

interface UpdateStudentParams {
  studentData: NewStudentData
}

export const studentsService = {
  getAll() {
    return http.get('/students/')
  },

  delete({ studentId }: DeleteStudentParams) {
    return http.delete(`/students/${studentId}`)
  },

  create({ newStudentData }: CreateParams) {
    const body = { ...newStudentData }

    return http.post('/students/', {
      ...body,
    })
  },

  updateStudent({ studentData }: UpdateStudentParams) {
    return http.put('/users/', {
      ...studentData,
    })
  },
}
