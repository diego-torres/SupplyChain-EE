export const MENU = [
  {
    path: '',
    children: [
      {
        path: 'home',
        data: {
          menu: {
            title: 'Tablero',
            icon: 'ion-android-home',
            selected: true,
            expanded: false,
            order: 0
          }
        }
      },
      {
        path: 'catalogs',
        data: {
          menu: {
            title: 'Catálogos',
            icon: 'ion-edit',
            selected: false,
            expanded: false,
            order: 100,
          }
        },
        children: [
          {
            path: 'parts',
            data: {
              menu: {
                title: 'Partes',
              }
            }
          },
          {
            path: 'companies',
            data: {
              menu: {
                title: 'Compañías',
              }
            }
          }
        ]
      },
      {
        path: 'packing-list',
        data: {
          menu: {
            title: 'Listas Empaque',
            icon: 'ion-ios-box-outline',
            selected: false,
            expanded: false,
            order: 250,
          }
        },
        children: [
          {
            path: 'all',
            data: {
              menu: {
                title: 'Todas',
              }
            }
          },
          {
            path: 'to-inbound',
            data: {
              menu: {
                title: 'Por Entrar',
              }
            }
          },
          {
            path: 'inbound',
            data: {
              menu: {
                title: 'Con Entrada',
              }
            }
          },
          {
            path: 'ph-rev',
            data: {
              menu: {
                title: 'Revisión Física',
              }
            }
          },
          {
            path: 'legal-rev',
            data: {
              menu: {
                title: 'Revisión Legal',
              }
            }
          }
        ]
      }
    ]
  }
];
